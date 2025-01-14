package com.example.geminiintegration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.0-pro",
        apiKey = "AIzaSyDqwWkqAI9m6X67Wl5OVxooXdKZ512fs60"
    )

    private val _chatState = MutableStateFlow<ChatUiState>(ChatUiState.Initial)
    val chatState: StateFlow<ChatUiState> = _chatState.asStateFlow()

    private val _userInput = MutableStateFlow("")
    val userInput: StateFlow<String> = _userInput.asStateFlow()

    fun onUserInputChanged(input: String) {
        _userInput.value = input
    }

    fun sendMessage() {
        val userMessage = _userInput.value
        if (userMessage.isBlank()) return

        viewModelScope.launch {
            _chatState.value = ChatUiState.Loading
            try {
                val response = generativeModel.generateContent(userMessage)
                _chatState.value = ChatUiState.Success(response.text ?: "No response")
            } catch (e: Exception) {
                _chatState.value = ChatUiState.Error(e.message ?: "Unknown error")
            }
            _userInput.value = ""
        }
    }
}