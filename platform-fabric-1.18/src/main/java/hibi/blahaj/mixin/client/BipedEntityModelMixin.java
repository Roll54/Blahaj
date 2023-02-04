package hibi.blahaj.mixin.client;

import hibi.blahaj.block.*;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.At.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(BipedEntityModel.class)
public class BipedEntityModelMixin {

	@Shadow
	public @Final ModelPart rightArm;

	@Shadow
	public @Final ModelPart leftArm;

	@Inject(
			method = {"positionRightArm", "positionLeftArm"},
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/render/entity/model/CrossbowPosing;hold(Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Z)V",
					shift = Shift.AFTER
			),
			cancellable = true
	)
	public void poseArms(LivingEntity entity, CallbackInfo ci) {
		if (entity.getMainHandStack().getItem() instanceof CuddlyItem || entity.getOffHandStack().getItem() instanceof CuddlyItem) {
			this.rightArm.pitch = -0.95F;
			this.rightArm.yaw = (float) (-Math.PI / 8);
			this.leftArm.pitch = -0.90F;
			this.leftArm.yaw = (float) (Math.PI / 8);
			ci.cancel();
		}
	}
}
