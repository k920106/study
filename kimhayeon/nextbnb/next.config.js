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

const withBundleAnalyzer = require('@next/bundle-analyzer')({
  enabled: process.env.ANALYZE === 'true',
})

// module.exports = nextConfig
module.exports = withBundleAnalyzer(nextConfig)
