package com.ccu.wulpass.rest.data

import org.jetbrains.annotations.NotNull

data class Auth(@NotNull val id: String, @NotNull val publicKey: String, @NotNull val type: String) {
}