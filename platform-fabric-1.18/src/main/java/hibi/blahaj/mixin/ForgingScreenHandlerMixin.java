package hibi.blahaj.mixin;

import net.minecraft.entity.player.*;
import net.minecraft.screen.*;
import org.spongepowered.asm.mixin.*;

@Mixin(ForgingScreenHandler.class)
public class ForgingScreenHandlerMixin {

	@Shadow
	protected @Final PlayerEntity player;
}
