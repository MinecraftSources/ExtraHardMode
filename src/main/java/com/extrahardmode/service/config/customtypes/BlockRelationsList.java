package com.extrahardmode.service.config.customtypes;


import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds a relationship. BlockTypes can be retrieved by their key BlockType. F.e stone -> cobblestone
 */
public class BlockRelationsList
{
    private Map<Integer, BlockRelation> mBlockRelations = new HashMap<Integer, BlockRelation>();
    /**
     * An empty list
     */
    public final static BlockRelationsList EMPTY_LIST = new BlockRelationsList();


    /**
     * Add Relationships from a string loaded from a config
     *
     * @param configString expected format: block1@meta-block2@meta. If it doesn't match it won't be added
     */
    public void addFromConfig(String configString)
    {
        if (configString == null)
            return;

        String[] splitted = configString.split("-");
        if (splitted.length < 2)
            return;

        BlockType block1 = BlockType.loadFromConfig(splitted[0]);
        BlockType block2 = BlockType.loadFromConfig(splitted[1]);
        if (block1.isValid() && block2.isValid())
            add(block1, block2);
    }


    /**
     * Saves this so it can be from the config again
     *
     * @return list of block relations
     */
    public String[] toConfigStrings()
    {
        String[] configStrings = new String[mBlockRelations.size()];
        int i = 0;
        for (BlockRelation relation : mBlockRelations.values())
        {
            configStrings[i] = relation.getKeyBlock().toString() + "-" + relation.getValueBlock().toString();
            i++;
        }
        return configStrings;
    }


    public void add(BlockType block1, BlockType block2)
    {
        mBlockRelations.put(block1.getBlockId(), new BlockRelation(block1, block2));
    }


    public boolean contains(Block block)
    {
        return mBlockRelations.containsKey(block.getTypeId());
    }


    public boolean contains(BlockType block1)
    {
        return mBlockRelations.containsKey(block1.getBlockId());
    }


    public BlockType get(BlockType block1)
    {
        return mBlockRelations.get(block1.getBlockId()).getValueBlock();
    }


    public BlockType get(Block block)
    {
        return mBlockRelations.get(block.getTypeId()).getValueBlock();
    }
}
