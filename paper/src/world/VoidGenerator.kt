package de.joker.glue.paper.world

import org.bukkit.World
import org.bukkit.generator.BlockPopulator
import org.bukkit.generator.ChunkGenerator

open class VoidGenerator : ChunkGenerator() {
    override fun shouldGenerateCaves(): Boolean = false
    override fun shouldGenerateDecorations(): Boolean = false
    override fun shouldGenerateMobs(): Boolean = false
    override fun shouldGenerateNoise(): Boolean = false
    override fun shouldGenerateStructures(): Boolean = false
    override fun shouldGenerateSurface(): Boolean = false
    override fun getDefaultPopulators(world: World): MutableList<BlockPopulator> {
        return mutableListOf()
    }
}