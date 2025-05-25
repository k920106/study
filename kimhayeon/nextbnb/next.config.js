/** @type {import('next').NextConfig} */
// const nextConfig = {}
const nextConfig = {
  images: {
    remotePatterns: [{
      hostname: 'loremflickr.com',
    },],
  },
};

module.exports = nextConfig
