A historically authentic dynamic price generator for Dungeons & Dragons in-game purchases. Implemented with 
agent-based trade simulation where local events impact market prices.

In Dungeons & Dragons, the price list is set in the back of the Player's Handbook. This seems wildly unrealistic 
since the players will influence local prices based on their actions. In order to properly simulate the local 
economy, we have chosen to begin with grain prices, which in a pre-modern economy are a good proxy for labor cost 
which in turn dictates other prices. We're using historical reference material relating to the ~16th Century in Western 
Europe (chosen for similarity to most D&D settings) such as [acoup.blog](http://acoup.blog/2020/07/24/collections-bread-how-did-they-make-it-part-i-farmers/) and [Fernand Braudel's work](http://www.ucpress.edu/book/9780520081154/civilization-and-capitalism-15th-18th-century-vol-ii) on historical markets to 
provide some authenticity to the results.

An example use case -

You're running a D&D game and the party stumbles upon a town which is hungry. Your cleric casts Create Food and 
Water a few times to feed the villagers. You continue on with your travels.

Later, you are called to battle a large dragon at a village near the town. It turns out that the lord of the nearby 
village usually sells grain to the townspeople. Since, however, they had excess food thanks to the party, the lord 
didn't make enough money to outfit the guards with armor and weapons fit to fight a dragon.

This is the sort of deeper, more meaningful interaction with the world that we're simulating

Road Map

Using JDK 15.0.1 and Intellij IDEA 2020.3.1

Open questions
- If there's an insufficient amount of labor to complete the sowing in one week, what is the likely outcome? Would they take help from other households or just sow later?
- How best to model the inefficiency in trade? Merchants visited infrequently and markets were small
- What were large landowners likely to be farming. What population did the manor usually contain?
- How did the process of plowing actually work? What downsides were there to plowing off peak? What if you plowed without a plow team?
- Would 3 field crop rotation fields be equivalent size?
- How much food would a lord have on hand vs wealth? How much would his household consume?