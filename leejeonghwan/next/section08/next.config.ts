import type { NextConfig } from "next";

const nextConfig: NextConfig = {
	images: {
		remotePatterns: [
			{
				protocol: "https",
				hostname: "shopping-phinf.pstatic.net",
				pathname: "**",
			}
		]
	},
  logging: {
		fetches: {
			fullUrl: true
		}
	}
};

export default nextConfig;
