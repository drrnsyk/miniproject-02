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

export interface Deal {
    dealID: string
    dealRating: string
    storeID: string
    gameID: string
    title: string
    metacriticScore: string
    isOnSale: string
    salePrice: string
    normalPrice: string
    thumb: string
}

export interface Game {
    gameID: string
    cheapest: string
    cheapestDealID: string
    external: string
    thumb: string
}

export interface Detail {
    storeID: string
    gameID: string
    name: string
    salePrice: string
    retailPrice: string
    steamRatingText: string
    steamRatingPercent: string
    steamRatingCount: string
    metacriticScore: string
    metacriticLink: string
    releaseDate: string
    publisher: string
    thumb: string
    cheaperDealID: string
    cheaperStoreID: string
    cheaperSalePrice: string
    cheapestPrice: string
    cheapestPriceDate: string
}

export interface Store {
    storeID: string
    storeName: string
    isActive: Number
    imagesBanner: string
    imagesLogo: string
    imagesIcon: string
}

export interface Stripeid {
    id: string
}