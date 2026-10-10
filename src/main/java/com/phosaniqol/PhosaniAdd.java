package com.phosaniqol;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.NPC;
import net.runelite.api.gameval.NpcID;

@Getter
@Setter
public class PhosaniAdd extends PhosaniNpc
{
	private final Map<Integer, PhosaniQolConfig.HighlightStyle> HIGHLIGHT_STYLE_MAP;

	public PhosaniAdd(NPC npc, PhosaniQolConfig config)
	{
		super.setNpc(npc);
		this.HIGHLIGHT_STYLE_MAP = new HashMap<>();
		this.HIGHLIGHT_STYLE_MAP.put(NpcID.NIGHTMARE_CHALLENGE_PARASITE, config.highlightParasite());
		this.HIGHLIGHT_STYLE_MAP.put(NpcID.NIGHTMARE_CHALLENGE_PARASITE_WEAK, config.highlightParasite());
		this.HIGHLIGHT_STYLE_MAP.put(NpcID.NIGHTMARE_CHALLENGE_HUSK_RANGED, config.highlightRangedHusk());
		this.HIGHLIGHT_STYLE_MAP.put(NpcID.NIGHTMARE_CHALLENGE_HUSK_MAGIC, config.highlightMagicHusk());
		this.HIGHLIGHT_STYLE_MAP.put(NpcID.NIGHTMARE_CHALLENGE_SLEEPWALKER, config.highlightSleepwalkers());
		setHighlightConfig(config);
	}

	public void setHighlightConfig(PhosaniQolConfig config)
	{
		int npcId = super.getNpc().getId();

		super.setHighlightConfig(
			HIGHLIGHT_STYLE_MAP.get(npcId),
			config.addsBorderWidth(),
			getAddHighlightColor(npcId, config),
			config.addsFillColor(),
			PhosaniQolConfig.OverlayType.NONE,
			null,
			0,
			null
		);
	}

	private Color getAddHighlightColor(int npcId, PhosaniQolConfig config)
	{
		switch (npcId)
		{
			case NpcID.NIGHTMARE_CHALLENGE_HUSK_RANGED:
				return config.rangedHuskColor();

			case NpcID.NIGHTMARE_CHALLENGE_HUSK_MAGIC:
				return config.magicHuskColor();

			case NpcID.NIGHTMARE_CHALLENGE_PARASITE:
			case NpcID.NIGHTMARE_CHALLENGE_PARASITE_WEAK:
				return config.parasiteColor();

			default:
				return config.sleepwalkerColor();
		}
	}
}