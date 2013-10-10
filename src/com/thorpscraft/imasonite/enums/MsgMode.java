package com.thorpscraft.imasonite.enums;


public enum MsgMode {
	INFO(TcChatColor.AQUA),
	INSTRUCTION(TcChatColor.YELLOW),
	WARNING(TcChatColor.GOLD),
	ERROR(TcChatColor.RED),
	SUCCESS(TcChatColor.GREEN);
	
	
	private TcChatColor chatColor;

	private MsgMode(TcChatColor chatColor) {
		this.chatColor = chatColor;
	}
	public String getColor() {
		return chatColor.toString();
	}

}
