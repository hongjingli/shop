package config;

import java.io.File;

public class Constant {
	public static class Path {

		//		先改成自己环境下的，部署时再改
		private static final String BASE = "E:/File/Code/Program/Eclipse/ct116";
		private static final String WEB = BASE + "/web";
		
		
		//
		private static final String USER_DATA = "/data/user";
		private static final String GOODS_IMG = USER_DATA + "/goods/img";
		
		public static String clientGoodsImg() {
			return GOODS_IMG;
		}
		public static String serverGoodsImg() {
			return WEB + GOODS_IMG;
		}
		
		
	}
	public static void build() {
		String server = Path.serverGoodsImg();
		File file = new File(server);
		if (!file.isDirectory() || !file.exists()) {
			file.mkdirs();
		}
	}
	public static void main(String [] args) {
		build();
	}
	
}
