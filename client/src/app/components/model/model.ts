export interface Status{
    statusCode: number,
    message: string
}

export interface SignupRequest {
    name: string
    email: string
    password: string
    confirmPassword: string
    role: string
}

export interface LoginRequest {
    email: string
    password: string
}

export interface ChangePassword {
    email: string
    currentPassword: string
    newPassword: string
    confirmNewPassword: string
}

export interface LoginResp extends Status{
    token: string
    // refreshToken: string
    // expiration: string
    // name: string
    // email: string
}

export interface Account {
    id: string
    email: string
    name: string
    password: string
    role: string
}