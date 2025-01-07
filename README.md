# Virtual Threaded Text Simplifier

## Dylan Boyle
**Student ID:** G00438786  
**Assignment Year:** SDDS Assignment 2024

---

## Application Overview
The Virtual Threaded Text Simplifier is a multi-threaded Java application designed to simplify text content by leveraging advanced similarity algorithms and configuration management. It offers a streamlined interface to configure settings, load data, and simplify text using various similarity-based approaches.

---

## Main Features

### 1. Text Simplification
- **Functionality:** Simplifies input text by replacing words with synonyms or related terms based on their similarity.
- **Replacement Strategies:**
  - **Most Similar:** Replaces words with the most similar alternatives.
  - **Least Similar:** Replaces words with the least similar alternatives.
  - **Random Replacement:** Randomly selects replacements from candidate words.

### 2. Similarity Algorithms
- **Algorithms Supported:**
  - Euclidean Distance
  - Cosine Similarity
  - Jaccard Similarity
  - Manhattan Distance
  - Pearson Correlation
  - Chebyshev Distance
- **Dynamic Configuration:**
  - Users can select and configure algorithms according to their preferences.

### 3. Configuration Management
- **Comprehensive Configuration Menu:**
  - Set paths for input and output files, word embeddings, and the Google 1000 common words file.
  - View and update current configurations directly from the console.
  - Reset all configurations to default settings.

### 4. Interactive Menu System
- **User-Friendly Interface:**
  - Clear and intuitive console navigation for all operations.
  - Dedicated menus for main features, configurations, and advanced settings.

### 5. Robust Error Handling
- **Validation:**
  - Handles invalid file paths and malformed inputs gracefully.
  - Ensures all configurations are validated to avoid runtime errors.
- **Feedback:**
  - Provides detailed and helpful feedback for user inputs.

### 6. Multi-threaded Loading
- **Performance Optimization:**
  - Accelerates the loading process for word embeddings and common words by leveraging multi-threading.

---

## Usage Instructions

1. Run the application using the command line:
   ```bash
   java -cp ./simplifier.jar ie.atu.sw.Runner
   ```
2. Use the main menu to configure settings and specify file paths.
3. Input text for simplification and view the results.
4. Navigate the configuration menu to refine settings as needed.

---

## Resources 
List of files to use for this App:
- [word-embeddings.txt](Resources/word-embeddings.txt): Word-embeddings file for uploading a path.
- [google-1000.txt](Resources/google-1000.txt): Google-1000 file for uploading a path.