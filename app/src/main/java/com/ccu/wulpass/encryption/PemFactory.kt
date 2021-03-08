package com.ccu.wulpass.encryption

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.*
import java.security.spec.RSAKeyGenParameterSpec
import java.util.*
import javax.crypto.Cipher
import javax.security.auth.x500.X500Principal

object PemFactory {
    private val keyStore = getKeyStore()
    private fun getKeyStore() : KeyStore {
        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)
        return keyStore
    }
    fun getBase64key(alias: String) : String{
        return encodeBase64(getPublicKey(alias).toString())
    }

    fun encryptContract(data: String):String{
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, (getKeyEntry("Org2")as KeyStore.PrivateKeyEntry).certificate.publicKey)
        val ciphertext = cipher.doFinal(data.toByteArray())
        return Base64.encodeToString(ciphertext,Base64.URL_SAFE)
    }
    fun decrpytContract(text: String):String{
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.DECRYPT_MODE, (getKeyEntry("Org2")as KeyStore.PrivateKeyEntry).certificate.publicKey)
        val tmp = Base64.decode(text,Base64.URL_SAFE)
        return String(cipher.doFinal(tmp))
    }
    fun generatePKI(alias: String){
        val kpg: KeyPairGenerator = KeyPairGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_EC,
            "AndroidKeyStore"
        )
        val parameterSpec: KeyGenParameterSpec = KeyGenParameterSpec.Builder(
            alias,
            KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY
        ).run {
            setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
            build()
        }

        kpg.initialize(parameterSpec)

        kpg.generateKeyPair()
    }
    fun generateEncryptKey(){
        val generator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA,"AndroidKeyStore")
        generator.initialize(KeyGenParameterSpec.Builder("Org2", KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setAlgorithmParameterSpec(RSAKeyGenParameterSpec(2048, RSAKeyGenParameterSpec.F4))
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
            .setDigests(KeyProperties.DIGEST_SHA512, KeyProperties.DIGEST_SHA384, KeyProperties.DIGEST_SHA256)
            .setUserAuthenticationRequired(false)
            .build()
        )
        generator.generateKeyPair()
    }
    fun signContract(data: String, alias: String): String{
        val entry = getKeyEntry(alias)
        if(entry !is KeyStore.PrivateKeyEntry){
            return "false"
        }
        val signature = Signature.getInstance("SHA256withECDSA")
            .run{
                initSign(entry.privateKey)
                update(data.toByteArray())
                sign()
            }
        return Base64.encodeToString(signature, Base64.URL_SAFE)
    }
    fun verifyContract(data:String, alias: String):Boolean{
        val entry = getKeyEntry(alias) as? KeyStore.PrivateKeyEntry
        return Signature.getInstance("SHA256withECDSA")
            .run {
                if (entry != null) {
                    initVerify(entry.certificate)
                }
                update(data.toByteArray())
                verify(data.toByteArray())
            }
    }
    private fun getKeyEntry(alias: String): KeyStore.Entry{
        return keyStore.getEntry(alias,null)
    }
    private fun getPublicKey(alias: String) : PublicKey{
        return keyStore.getCertificate(alias).publicKey
    }
    private fun initGeneratorWithKeyPairGeneratorSpec(generator: KeyPairGenerator, alias: String):KeyGenParameterSpec{
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.YEAR,3)

        return KeyGenParameterSpec
            .Builder(alias, KeyProperties.PURPOSE_SIGN or  KeyProperties.PURPOSE_VERIFY)
            .setCertificateSubject(X500Principal("CN=${alias} CA Certificate"))
            .setKeyValidityStart(startDate.time)
            .setKeyValidityEnd(endDate.time)
            .run {
                setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                build()
            }
    }
    private fun encodeBase64(key: String) : String{
        return String(Base64.encode(key.toByteArray(), Base64.URL_SAFE))
    }
}