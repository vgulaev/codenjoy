package com.codenjoy.dojo.minesweeper;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.LocalGameRunner;
import com.codenjoy.dojo.minesweeper.client.Board;
import com.codenjoy.dojo.minesweeper.client.ai.AISolver;
import com.codenjoy.dojo.minesweeper.services.GameRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.multiplayer.GameField;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class SmokeTest {
    private int index;

    @Test
    public void test() {
        // given
        List<String> messages = new LinkedList<>();

        LocalGameRunner.timeout = 0;
        LocalGameRunner.out = (e) -> messages.add(e);
        LocalGameRunner.countIterations = 225;

        Dice dice = LocalGameRunner.getDice(1);

        GameRunner gameType = new GameRunner() {
            @Override
            public Dice getDice() {
                return dice;
            }

            @Override
            public GameField createGame(int levelNumber) {
                settings.addEditBox("Board size").type(Integer.class).update(7);
                settings.addEditBox("Mines on board").type(Integer.class).update(2);

                return super.createGame(levelNumber);
            }
        };

        // when
        LocalGameRunner.run(gameType,
                new AISolver("test", dice),
                new Board());

        // then
//        assertEquals("",
//                String.join("\n", messages));

    }
}
