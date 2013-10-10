
package com.thorpscraft.imasonite.abstraction.enums;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;

/** All supported color values for chat and some methods to colorise/decolorise
 * 
 * @author Michael Mason */
public enum TcChatColor {
	BLACK(0x0),
	DARK_BLUE(0x1),
	DARK_GREEN(0x2),
	DARK_AQUA(0x3),
	DARK_RED(0x4),
	DARK_PURPLE(0x5),
	GOLD(0x6),
	GRAY(0x7),
	DARK_GRAY(0x8),
	BLUE(0x9),
	GREEN(0xA),
	AQUA(0xB),
	RED(0xC),
	LIGHT_PURPLE(0xD),
	YELLOW(0xE),
	WHITE(0xF),
	RANDOM('k'),
	BOLD('l'),
	STRIKETHROUGH('m'),
	UNDERLINE('n'),
	ITALIC('o'),
	PLAIN_WHITE('r');
	
	private final char									code;
	
	private final static Map<Character, TcChatColor>	charColors	= new HashMap<Character, TcChatColor>();
	
	private TcChatColor(char code) {
		this.code = code;
	}
	
	private TcChatColor(int code) {
		this.code = Integer.toHexString(code).toLowerCase().charAt(0);
	}
	
	public char getChar() {
		return code;
	}
	
	@Override
	public String toString() {
		return String.format("\u00A7%s", code);
	}
	
	public static TcChatColor getByChar(char code) {
		return charColors.get(Character.valueOf(code));
	}
	
	/** Colourises a string (using '&' character)
	 * 
	 * @param string String to colourise
	 * @return Colourised string */
	public static String giveColor(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	/** Strips the given message of all color codes (using '&' character)
	 * 
	 * @param input String to strip of color
	 * @return A copy of the input string, without any color codes */
	public static String takeColor(final String input) {
		if (input == null) {
			return null;
		}
		return input.replaceAll("(?i)\u00A7[0-9A-Fklmnor]", "");
	}
	
	static {
		for (TcChatColor color : TcChatColor.values()) {
			charColors.put(color.getChar(), color);
		}
	}
}
