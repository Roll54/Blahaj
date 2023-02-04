package hibi.blahaj.mixin.client;

import hibi.blahaj.block.*;
import net.minecraft.client.network.*;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

	@Inject(
			method = "getArmPose(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;",
			at = @At("TAIL"),
			cancellable = true
	)
	private static void cuddleBlahaj(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> ci) {
		ItemStack lv = player.getStackInHand(hand);
		if (lv.getItem() instanceof CuddlyItem) {
			ci.setReturnValue(ArmPose.CROSSBOW_HOLD);
			ci.cancel();
		}
	}

}
