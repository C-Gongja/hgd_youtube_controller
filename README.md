# Youtube Controller using hand gesture recognition

This repository features a YouTube Controller using hand gesture recognition, employing the MediaPipe library for Python. I use the pre-trained hand gesture detection (hgd) model that was initially developed by [Kazuhito00](https://github.com/Kazuhito00/hand-gesture-recognition-using-mediapipe). I used [translated version](https://github.com/kinivi/hand-gesture-recognition-mediapipe).I developed a Java-based YouTube video controller to extract outputs from a Python-based HGD and integrate them seamlessly.

### Project Overview
* Hand Gesture Recognition with MediaPipe:

* Utilizes the MediaPipe library in Python for robust hand gesture recognition.
The hgd model, authored by Kazuhito00, forms the foundation for accurate gesture detection.
Integration with YouTube Controller (Java):

  * The original hgd model codes have been modified to extract outputs for integration with a YouTube video controller written in Java.
    The communication between Python and Java is facilitated using ProcessBuilder.
    Threaded Operation for Consistent Outputs:

* Implements threads to ensure consistent retrieval of hgd model outputs.
Utilizes the extracted gesture information to control various aspects of the YouTube video player.
