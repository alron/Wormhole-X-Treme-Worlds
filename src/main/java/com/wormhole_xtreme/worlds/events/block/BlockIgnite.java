/*
 * Wormhole X-Treme Worlds Plugin for Bukkit
 * Copyright (C) 2011 Dean Bailey
 * 
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.wormhole_xtreme.worlds.events.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;

import com.wormhole_xtreme.worlds.world.WorldManager;
import com.wormhole_xtreme.worlds.world.WormholeWorld;

/**
 * The Class BlockIgnite.
 * 
 * @author alron
 */
class BlockIgnite {

    /**
     * Handle block ignite.
     * 
     * @param block
     *            the block
     * @param igniteCause
     *            the ignite cause
     * @return true, if successful
     */
    static boolean handleBlockIgnite(final Block block, final IgniteCause igniteCause) {
        final String worldName = block.getWorld().getName();
        if (WorldManager.isWormholeWorld(worldName)) {
            final WormholeWorld wormholeWorld = WorldManager.getWormholeWorld(worldName);
            if ( !wormholeWorld.isWorldAllowFire()) {
                return true;
            }
            else if (igniteCause != null) {
                switch (igniteCause) {
                    case LAVA :
                        return wormholeWorld.isWorldAllowLavaFire() ? false : true;
                    case SPREAD :
                        return wormholeWorld.isWorldAllowFireSpread() ? false : true;
                    case LIGHTNING :
                        return wormholeWorld.isWorldAllowLightningFire() ? false : true;
                    case FLINT_AND_STEEL :
                        return wormholeWorld.isWorldAllowPlayerStartFire() ? false : true;
                    default :
                        break;
                }
            }
        }
        return false;
    }
}