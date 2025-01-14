package com.example.geminiintegration

sealed class ChatUiState {
    object Initial : ChatUiState()
    object Loading : ChatUiState()
    data class Success(val response: String) : ChatUiState()
    data class Error(val message: String) : ChatUiState()
}