const nextConfig = {
  images: {
    remotePatterns: [
      {
        hostname: 'loremflickr.com',
      },
      {
        hostname: 'a0.muscache.com',
      },
      {
        hostname: 'firebasestorage.googleapis.com',
      },
      {
        hostname: 'k.kakaocdn.net',
      },
    ],
  },
}

module.exports = nextConfig
