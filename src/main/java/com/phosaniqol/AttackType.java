package com.phosaniqol;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import java.util.function.Function;
import lombok.Getter;
import net.runelite.api.gameval.AnimationID;

@Getter
public enum AttackType
{
	POWERED_STAFF(ImmutableSet.of(
		AnimationID.POG_WARPED_SCEPTRE_ATTACK,
		//AnimationID.HUMAN_CASTWAVE_STAFF_WALKMERGE,
		//AnimationID.HUMAN_CASTWAVE_STAFF,
		AnimationID.HUMAN_SPECIAL_ACCURSED,
		AnimationID.HUMAN_LITHIC_SCEPTRE_SPEC_01
	), (d) -> 1 + ((1 + d) / 3)),
	EYE_OF_AYAK(ImmutableSet.of(
		AnimationID.HUMAN_EYE_OF_AYAK_NORMAL,
		AnimationID.HUMAN_EYE_OF_AYAK_SPECIAL
	), (d) -> d < 3 ? 1 : 2),
	TUMEKENS_SHADOW(ImmutableSet.of(
		AnimationID.TOA_SOT_CAST_B
	), (d) -> 2 + ((1 + d) / 3)),
	SPELL(ImmutableSet.of(
		AnimationID.HUMAN_CASTING,
		AnimationID.HUMAN_CASTIBANBLAST,
		AnimationID.SLAYER_MAGICDART_CAST,
		AnimationID.HUMAN_CASTSTRIKE,
		AnimationID.HUMAN_CASTSTRIKE_WALKMERGE,
		AnimationID.HUMAN_CASTSTRIKE_STAFF,
		AnimationID.HUMAN_CASTSTRIKE_STAFF_WALKMERGE,
		AnimationID.HUMAN_CASTWAVE_WALKMERGE,
		AnimationID.HUMAN_CASTWAVE_STAFF_WALKMERGE,
		AnimationID.HUMAN_CAST_SURGE_WALKMERGE,
		AnimationID.ZAROS_VERTICAL_CASTING_WALKMERGE,
		AnimationID.ZAROS_CASTING_WALKMERGE
	), (d) -> 1 + ((1 + d) / 3)),
	BOW(ImmutableSet.of(
		AnimationID.HUMAN_WEAPON_BOW_VENATOR01_SHOOT,
		AnimationID.HUMAN_BOW, // TODO: dark bow 2nd arrow delay
		AnimationID.HUMAN_SPECIAL01_WEBWEAVER,
		AnimationID.XBOWS_HUMAN_FIRE_AND_RELOAD_PVN, // TODO: acb spec = 2t delay
		AnimationID.HUMAN_CROSSBOW,
		AnimationID.ZCB_ATTACK_PVN,
		AnimationID.BARROWS_REPEATING_CROSSBOW_FIRE,
		AnimationID.HUMAN_ATLATL_ATTACK_RANGED_01
	), (d) -> 1 + ((3 + d) / 6)),
	THROWN(ImmutableSet.of(
		AnimationID.SNAKEBOSS_BLOWPIPE_ATTACK,
		AnimationID.CAMPHOR_BLOWPIPE_ATTACK,
		AnimationID.DRYGORE_BLOWPIPE_ATTACK,
		AnimationID.IRONWOOD_BLOWPIPE_ATTACK,
		AnimationID.ROSEWOOD_BLOWPIPE_ATTACK,
		AnimationID.ROSEWOOD_BLOWPIPE_SPECIAL_ATTACK,
		AnimationID.SNAKEBOSS_BLOWPIPE_ATTACK_ORNAMENT,
		AnimationID.II_HUMAN_DART_THROW_PVN,
		AnimationID.HUMAN_STAKE2_PVN,
		AnimationID.HUMAN_DRAGON_TAXE_SPEC,
		AnimationID.HUMAN_CHINCHOMPA_ATTACK_PVN,
		AnimationID.HUMAN_DRAGON_KNIFE,
		AnimationID.HUMAN_DRAGON_KNIFE_P,
		AnimationID.HUMAN_DRAGON_TKNIVES_SPEC,
		AnimationID.HUMAN_DRAGON_TKNIVES_SPEC_POISON,
		AnimationID.HUMAN_GLAIVE_RALOS01_CHARGED_THROW,
		AnimationID.HUMAN_GLAIVE_RALOS01_CHARGED_SPECIAL,
		AnimationID.HUMAN_GLAIVE_RALOS01_UNCHARGED_THROW,
		AnimationID.HUMAN_GLAIVE_RALOS01_UNCHARGED_SPECIAL,
		AnimationID.HUMAN_STAKE2,
		AnimationID.THZARR_RING_CHUCK_PVN
	), (d) -> 1 + (d / 6)),
	BALLISTA(ImmutableSet.of(
		AnimationID.BALLISTA_ATTACK_PVN
	), (d) -> (d < 5) ? 1 : 2),
	MELEE(ImmutableSet.of(), (d) -> 0),
	DEMONBANE_SPELL(ImmutableSet.of(
		AnimationID.HUMAN_SPELLCAST_DEMONBANE
	), (d) -> 2),
	UNDEAD_GRASP(ImmutableSet.of(
		AnimationID.HUMAN_SPELLCAST_GRASP
	), (d) -> 1);

	private final Set<Integer> animationIds;
	private final Function<Integer, Integer> hitDelay;

	AttackType(Set<Integer> animationIds, Function<Integer, Integer> hitDelay)
	{
		this.animationIds = animationIds;
		this.hitDelay = hitDelay;
	}

	public boolean contains(int animationId)
	{
		return animationIds.contains(animationId);
	}

	public Integer apply(int distance)
	{
		return hitDelay.apply(distance);
	}
}
