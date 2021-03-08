/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.chilkatsoft;

public class CkCertChain {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected CkCertChain(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(CkCertChain obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        chilkatJNI.delete_CkCertChain(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public CkCertChain() {
    this(chilkatJNI.new_CkCertChain(), true);
  }

  public void LastErrorXml(CkString str) {
    chilkatJNI.CkCertChain_LastErrorXml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void LastErrorHtml(CkString str) {
    chilkatJNI.CkCertChain_LastErrorHtml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void LastErrorText(CkString str) {
    chilkatJNI.CkCertChain_LastErrorText(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void get_DebugLogFilePath(CkString str) {
    chilkatJNI.CkCertChain_get_DebugLogFilePath(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String debugLogFilePath() {
    return chilkatJNI.CkCertChain_debugLogFilePath(swigCPtr, this);
  }

  public void put_DebugLogFilePath(String newVal) {
    chilkatJNI.CkCertChain_put_DebugLogFilePath(swigCPtr, this, newVal);
  }

  public void get_LastErrorHtml(CkString str) {
    chilkatJNI.CkCertChain_get_LastErrorHtml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorHtml() {
    return chilkatJNI.CkCertChain_lastErrorHtml(swigCPtr, this);
  }

  public void get_LastErrorText(CkString str) {
    chilkatJNI.CkCertChain_get_LastErrorText(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorText() {
    return chilkatJNI.CkCertChain_lastErrorText(swigCPtr, this);
  }

  public void get_LastErrorXml(CkString str) {
    chilkatJNI.CkCertChain_get_LastErrorXml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorXml() {
    return chilkatJNI.CkCertChain_lastErrorXml(swigCPtr, this);
  }

  public boolean get_LastMethodSuccess() {
    return chilkatJNI.CkCertChain_get_LastMethodSuccess(swigCPtr, this);
  }

  public void put_LastMethodSuccess(boolean newVal) {
    chilkatJNI.CkCertChain_put_LastMethodSuccess(swigCPtr, this, newVal);
  }

  public int get_NumCerts() {
    return chilkatJNI.CkCertChain_get_NumCerts(swigCPtr, this);
  }

  public int get_NumExpiredCerts() {
    return chilkatJNI.CkCertChain_get_NumExpiredCerts(swigCPtr, this);
  }

  public boolean get_ReachesRoot() {
    return chilkatJNI.CkCertChain_get_ReachesRoot(swigCPtr, this);
  }

  public boolean get_VerboseLogging() {
    return chilkatJNI.CkCertChain_get_VerboseLogging(swigCPtr, this);
  }

  public void put_VerboseLogging(boolean newVal) {
    chilkatJNI.CkCertChain_put_VerboseLogging(swigCPtr, this, newVal);
  }

  public void get_Version(CkString str) {
    chilkatJNI.CkCertChain_get_Version(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String version() {
    return chilkatJNI.CkCertChain_version(swigCPtr, this);
  }

  public CkCert GetCert(int index) {
    long cPtr = chilkatJNI.CkCertChain_GetCert(swigCPtr, this, index);
    return (cPtr == 0) ? null : new CkCert(cPtr, true);
  }

  public boolean IsRootTrusted(CkTrustedRoots trustedRoots) {
    return chilkatJNI.CkCertChain_IsRootTrusted(swigCPtr, this, CkTrustedRoots.getCPtr(trustedRoots), trustedRoots);
  }

  public boolean LoadX5C(CkJsonObject jwk) {
    return chilkatJNI.CkCertChain_LoadX5C(swigCPtr, this, CkJsonObject.getCPtr(jwk), jwk);
  }

  public boolean SaveLastError(String path) {
    return chilkatJNI.CkCertChain_SaveLastError(swigCPtr, this, path);
  }

  public boolean VerifyCertSignatures() {
    return chilkatJNI.CkCertChain_VerifyCertSignatures(swigCPtr, this);
  }

}
