package com.babybloom.web.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigUtility {
	private static volatile boolean isInited = false;
	private static Properties pros = new Properties();

	private ConfigUtility() {
	}

	public static boolean isInited() {
		return isInited;
	}

	public static boolean init(String filePath) {
		if (!isInited) {
			InputStream is = null;
			try {
				try {
					is = new FileInputStream(filePath);
				} catch (FileNotFoundException e) {
					is = ConfigUtility.class.getClassLoader().getResourceAsStream(filePath);
				}

				try {
					pros.load(is);
					isInited = true;
					return true;
				} catch (Exception e) {
					return false;
				}
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			return true;
		}
	}

	public static String getProperty(String key) {
		if (!isInited) {
			return null;
		}
		return pros.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		if (!isInited) {
			return defaultValue;
		}
		String prop = pros.getProperty(key);
		if (prop == null) {
			prop = defaultValue;
		}
		return prop;
	}

	public static int getPropertyAsInt(String key, int defaultValue) {
		try {
			return Integer.parseInt(getProperty(key));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static int getPropertyAsPositiveInt(String key, int defaultValue) {
		try {
			int rtn = Integer.parseInt(getProperty(key));
			if (rtn <= 0) {
				rtn = defaultValue;
			}
			return rtn;
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static long getPropertyAsLong(String key, long defaultValue) {
		try {
			return Long.parseLong(getProperty(key));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static long getPropertyAsPositiveLong(String key, long defaultValue) {
		try {
			long rtn = Long.parseLong(getProperty(key));
			if (rtn <= 0) {
				rtn = defaultValue;
			}
			return rtn;
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static boolean getPropertyAsBoolean(String key, boolean defaultValue) {
		try {
			return Boolean.parseBoolean(getProperty(key));
		} catch (Exception e) {
			return defaultValue;
		}
	}
}