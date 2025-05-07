package com.demo.helpers;

import java.util.HashMap;
import java.util.Map;

public class LoginAttemptTracker {
    private static final int MAX_ATTEMPTS = 5;
    private static final int LOCK_TIME = 30; // in seconds

    private static Map<String, Integer> attempts = new HashMap<>();
    private static Map<String, Long> lockoutEndTime = new HashMap<>();

    public static synchronized boolean isLocked(String username) {
        if (lockoutEndTime.containsKey(username)) {
            long endTime = lockoutEndTime.get(username);
            if (System.currentTimeMillis() < endTime) {
                return true; // Still locked
            } else {
                // Unlock the user
                lockoutEndTime.remove(username);
                attempts.remove(username);
            }
        }
        return false;
    }

    public static synchronized void loginFailed(String username) {
        int attemptCount = attempts.getOrDefault(username, 0) + 1;
        attempts.put(username, attemptCount);

        if (attemptCount >= MAX_ATTEMPTS) {
            lockoutEndTime.put(username, System.currentTimeMillis() + (LOCK_TIME * 1000));
        }
    }

    public static synchronized void loginSuccessful(String username) {
        attempts.remove(username);
        lockoutEndTime.remove(username);
    }

    public static synchronized long getLockoutTimeRemaining(String username) {
        if (lockoutEndTime.containsKey(username)) {
            long endTime = lockoutEndTime.get(username);
            return Math.max(0, (endTime - System.currentTimeMillis()) / 1000); // Return seconds remaining
        }
        return 0;
    }
}
