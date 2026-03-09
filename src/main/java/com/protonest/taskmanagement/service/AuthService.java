package com.protonest.taskmanagement.service;

import com.protonest.taskmanagement.dto.*;

public interface AuthService {

    void register(RegisterRequest request);

    AuthResponse login(AuthRequest request);
}