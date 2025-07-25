/** @type {import('next').NextConfig} */
const nextConfig = {
  images: {
    remotePatterns: [
      {
        hostname: 'loremflickr.com',
      },
      {
        hostname: 'a0.muscache.com',
      },
    ],
  },
};

module.exports = nextConfig
