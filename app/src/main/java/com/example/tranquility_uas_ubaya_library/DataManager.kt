package com.example.tranquility_uas_ubaya_library

class DataManager private constructor() {

    // Global data properties
    var username: String = ""

    companion object {
        // Singleton instance
        @Volatile
        private var instance: DataManager? = null

        fun getInstance(): DataManager =
            instance ?: synchronized(this) {
                instance ?: DataManager().also { instance = it }
            }
    }
}
