/*
 * This file is part of
 * MegaHardCore Server Plugin for Minecraft
 *
 * Copyright (C) 2012 Ryan Hamshire
 * Copyright (C) 2013 Diemex
 *
 * MegaHardCore is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaHardCore is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero Public License
 * along with MegaHardCore.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.megahardcore.mocks;


import com.megahardcore.MegaHardCore;

import static org.powermock.api.mockito.PowerMockito.mock;

/**
 *
 */
public class MockMegaHardCore
{
    private MegaHardCore mhc;


    public MegaHardCore get ()
    {
        mhc = mock(MegaHardCore.class);
        return mhc;
    }
}
