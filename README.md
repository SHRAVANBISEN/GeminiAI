# 🤖 Gemini AI Android Integration

<div align="center">
  <h2>🧠 Intelligent Chat Integration for Android</h2>
  <p>A lightweight Android library that enables you to quickly integrate Google's Gemini AI into your Android applications using Kotlin and Jetpack Compose.</p>
  
  ![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
  ![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
  ![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
  ![Gemini AI](https://img.shields.io/badge/Gemini_AI-8E75B2?style=for-the-badge&logo=google&logoColor=white)
</div>

## ✨ Features

- 💬 Simple text chat interface with Gemini AI
- 🎨 Modern Jetpack Compose UI
- ⚡ Real-time AI responses
- 🧩 Proper state management with loading and error states
- 📱 Easy-to-follow code structure
- 📦 Minimal dependencies

## 📋 Prerequisites

- Android Studio Giraffe (2023.1.1) or newer
- Kotlin 1.9.0 or newer
- Android min SDK 21 (Android 5.0)
- A Gemini API key from Google AI Studio

## 🚀 Getting Started

### Step 1: Clone the repository

```bash
git clone https://github.com/SHRAVANBISEN/GeminiAI.git
```

### Step 2: Get a Gemini API Key

1. Go to [Google AI Studio](https://aistudio.google.com/app/apikey)
2. Sign in with your Google account
3. Select or create a Google Cloud project
4. Create a new API key
5. Copy the API key

### Step 3: Add your API key to the project

Open `ChatViewModel.kt` and replace the placeholder API key with your own:

```kotlin
private val generativeModel = GenerativeModel(
    modelName = "gemini-1.0-pro",
    apiKey = "YOUR_API_KEY_HERE" // Replace with your API key
)
```

> ⚠️ **Security Note**: For production applications, consider storing your API key more securely using encrypted shared preferences, Android Keystore, or BuildConfig variables.

### Step 4: Build and run the application

Build and run the app on an emulator or physical device.

## 🔧 How to Use in Your Project

### Option 1: Copy the required files

1. Add the required dependencies to your project's `build.gradle`:

```gradle
dependencies {
    implementation "com.google.ai.client.generativeai:generativeai:0.1.1"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"
    implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0"
    implementation "androidx.lifecycle:lifecycle-runtime-compose:2.7.0"
}
```

2. Copy the following files to your project:
   - `ChatViewModel.kt`: Handles the AI communication logic
   - `ChatUiState.kt`: Manages UI states
   - `ChatScreen.kt`: Contains the Compose UI

3. Update the package names in the copied files to match your project

### Option 2: Include as a module

1. Import the project as a module in your existing Android project
2. Add the module dependency in your app's `build.gradle`:

```gradle
dependencies {
    implementation project(':gemini-integration')
}
```

## 🎨 Customization

### Change the UI

Modify `ChatScreen.kt` to adjust the UI to match your app's design:

```kotlin
@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = viewModel()
) {
    // Customize the UI here
}
```

### Modify the prompt handling

Update `ChatViewModel.kt` to change how prompts are processed:

```kotlin
fun sendMessage() {
    val userMessage = _userInput.value
    if (userMessage.isBlank()) return

    viewModelScope.launch {
        _chatState.value = ChatUiState.Loading
        try {
            // Customize the prompt or add system instructions here
            val response = generativeModel.generateContent(userMessage)
            _chatState.value = ChatUiState.Success(response.text ?: "No response")
        } catch (e: Exception) {
            _chatState.value = ChatUiState.Error(e.message ?: "Unknown error")
        }
        _userInput.value = ""
    }
}
```

## 🛠️ Troubleshooting

### API key issues

If you encounter "Invalid API key" errors:
- Make sure your API key is correctly copied
- Check that your Google Cloud project has billing enabled
- Verify the API key hasn't expired or been revoked

### Model errors

If you see "Model not found" errors:
- Ensure you're using the correct model name: "gemini-1.0-pro"
- Check if your API key has access to the model you're requesting

Newer versions of the API may use different model names. Check the [official documentation](https://ai.google.dev/models/gemini) for the most current model names.

### Quota limits

If you receive "Quota exceeded" errors:
- Check your usage in the Google Cloud Console
- Consider upgrading your plan

## 🔮 Next Steps

- Add conversation history
- Implement streaming responses
- Support for images with Gemini Pro Vision
- Enhance UI with chat bubbles and animations

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgements

- Google Gemini AI team for providing the API
- Jetpack Compose for the modern UI toolkit
