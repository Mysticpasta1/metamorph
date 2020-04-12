package mchorse.vanilla_pack.editors.panels;

import mchorse.mclib.client.gui.framework.elements.buttons.GuiSlotElement;
import mchorse.mclib.client.gui.framework.elements.utils.GuiInventoryElement;
import mchorse.metamorph.client.gui.editor.GuiAbstractMorph;
import mchorse.metamorph.client.gui.editor.GuiMorphPanel;
import mchorse.vanilla_pack.morphs.ItemStackMorph;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiItemStackPanel extends GuiMorphPanel<ItemStackMorph, GuiAbstractMorph<? extends ItemStackMorph>>
{
	public GuiSlotElement slot;
	public GuiInventoryElement inventory;

	public GuiItemStackPanel(Minecraft mc, GuiAbstractMorph<? extends ItemStackMorph> editor)
	{
		super(mc, editor);

		this.inventory = new GuiInventoryElement(mc, (stack) ->
		{
			this.slot.stack = stack;
			this.morph.setStack(stack);
			this.inventory.unlink();
		});
		this.inventory.setVisible(false);

		this.slot = new GuiSlotElement(mc, 0, this.inventory::link);

		this.inventory.flex().relative(this.slot.area).x(0.5F, 0).y(-5).wh(200, 100).anchor(0.5F, 1);
		this.slot.flex().relative(this.area).x(0.5F, 0).y(1, -10).wh(32, 32).anchor(0.5F, 1);

		this.add(this.slot, this.inventory);
	}

	@Override
	public void fillData(ItemStackMorph morph)
	{
		super.fillData(morph);

		this.slot.stack = morph.getStack();
	}
}