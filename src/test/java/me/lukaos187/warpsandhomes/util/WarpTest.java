package me.lukaos187.warpsandhomes.util;


import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import me.lukaos187.warpsandhomes.WarpsAndHomes;
import org.bukkit.Location;
import org.bukkit.Material;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class WarpTest {

    private static ServerMock server;
    private static WarpsAndHomes plugin;

    @BeforeAll
    public static void setUp(){
        server = MockBukkit.mock();
        plugin = MockBukkit.load(WarpsAndHomes.class);
    }

    @Test
    public void testNameGetters(){

        PlayerMock player1 = server.addPlayer("1");
        PlayerMock player2 = server.addPlayer("2");

        Warp warp = new Warp("TestWarp", "This is warp 1", player1, true, new Location(player1.getWorld(), 20, 20, 20, 20, 20), null);
        Warp warp2 = new Warp("Bli*bla-bluB1", "This is warp 2", player2, false, new Location(player2.getWorld(), 1, 1, 1, 1, 1), Material.MAGENTA_BED);

        Assertions.assertEquals("TestWarp", warp.getName());
        Assertions.assertEquals("Bli*bla-bluB1", warp2.getName());
    }

    @Test
    public void testNameSetters(){

        PlayerMock player1 = server.addPlayer("1");
        PlayerMock player2 = server.addPlayer("2");

        Warp warp = new Warp("TestWarp", "This is warp 1", player1, true, new Location(player1.getWorld(), 20, 20, 20, 20, 20), null);
        Warp warp2 = new Warp("Bli*bla-bluB1", "This is warp 2", player2, false, new Location(player2.getWorld(), 1, 1, 1, 1, 1), Material.MAGENTA_BED);

        warp.setName("hahahah");
        warp2.setName("TesTing23?..");

        Assertions.assertEquals("hahahah", warp.getName());
        Assertions.assertEquals("TesTing23?..", warp2.getName());
    }

    @Test
    public void testDescriptionGetters(){

        PlayerMock player1 = server.addPlayer("1");
        PlayerMock player2 = server.addPlayer("2");

        Warp warp = new Warp("warp1", "This is a very complex description..", player1, true, new Location(player1.getWorld(), 20, 20, 20, 20, 20), null);
        Warp warp2 = new Warp("warp2", "This is my warp for the trading hall.", player2, false, new Location(player2.getWorld(), 1, 1, 1, 1, 1), Material.MAGENTA_BED);

        Assertions.assertEquals("This is a very complex description..", warp.getDescription());
        Assertions.assertEquals("This is my warp for the trading hall.", warp2.getDescription());
    }

    @Test
    public void testDescriptionSetters(){

        PlayerMock player1 = server.addPlayer("1");
        PlayerMock player2 = server.addPlayer("2");

        Warp warp = new Warp("warp1", "This is a very complex description..", player1, true, new Location(player1.getWorld(), 20, 20, 20, 20, 20), null);
        Warp warp2 = new Warp("warp2", "This is my warp for the trading hall.", player2, false, new Location(player2.getWorld(), 1, 1, 1, 1, 1), Material.MAGENTA_BED);

        warp.setDescription("Hellllooooo????");
        warp2.setDescription("What would be a good description..?");

        Assertions.assertEquals("Hellllooooo????", warp.getDescription());
        Assertions.assertEquals("What would be a good description..?", warp2.getDescription());
    }

    @Test
    public void testOwnerGetters(){

        PlayerMock player1 = server.addPlayer("1");
        PlayerMock player2 = server.addPlayer("2");

        Warp warp = new Warp("warp1", "Fubar", player1, true, new Location(player1.getWorld(), 20, 20, 20, 20, 20), null);
        Warp warp2 = new Warp("warp2", "Fubar", player2, false, new Location(player2.getWorld(), 1, 1, 1, 1, 1), Material.MAGENTA_BED);

        Assertions.assertEquals(player1, warp.getOwner());
        Assertions.assertEquals(player2, warp2.getOwner());
    }

    @Test
    public void testOwnerSetters(){

        PlayerMock player1 = server.addPlayer("1");
        PlayerMock player2 = server.addPlayer("2");

        Warp warp = new Warp("warp1", "Fubar", player1, true, new Location(player1.getWorld(), 20, 20, 20, 20, 20), null);
        Warp warp2 = new Warp("warp2", "Fubar", player2, false, new Location(player2.getWorld(), 1, 1, 1, 1, 1), Material.MAGENTA_BED);
        Warp warp3 = new Warp("warp3", "Fubar", player2, false, new Location(player2.getWorld(), 341, 341, 123, 14, 13), Material.ANGLER_POTTERY_SHERD);

        warp.setOwner(player2);
        warp2.setOwner(player1);
        warp3.setOwner(player2);

        Assertions.assertEquals(player2, warp.getOwner());
        Assertions.assertEquals(player1, warp2.getOwner());
        Assertions.assertEquals(player2, warp3.getOwner());
    }

    @Test
    public void testIsPrivateGetters(){

        PlayerMock player1 = server.addPlayer("1");
        PlayerMock player2 = server.addPlayer("2");

        Warp warp = new Warp("warp1", "Fubar", player1, true, new Location(player1.getWorld(), 20, 20, 20, 20, 20), null);
        Warp warp2 = new Warp("warp2", "Fubar", player2, false, new Location(player2.getWorld(), 1, 1, 1, 1, 1), Material.MAGENTA_BED);

        Assertions.assertTrue(warp.isPrivate());
        Assertions.assertFalse(warp2.isPrivate());
    }

    @Test
    public void testPrivateSetters(){

        PlayerMock player1 = server.addPlayer("1");
        PlayerMock player2 = server.addPlayer("2");

        Warp warp = new Warp("warp1", "Fubar", player1, true, new Location(player1.getWorld(), 20, 20, 20, 20, 20), null);
        Warp warp2 = new Warp("warp2", "Fubar", player2, false, new Location(player2.getWorld(), 1, 1, 1, 1, 1), Material.MAGENTA_BED);

        warp.setPrivate(false);
        warp2.setPrivate(true);

        Assertions.assertFalse(warp.isPrivate());
        Assertions.assertTrue(warp2.isPrivate());
    }

    @Test
    public void testLocationGetters(){

        PlayerMock player1 = server.addPlayer("1");
        PlayerMock player2 = server.addPlayer("2");

        Warp warp = new Warp("warp1", "Fubar", player1, true, new Location(player1.getWorld(), 2023, 35, 436, 93, 23), null);
        Warp warp2 = new Warp("warp2", "Fubar", player2, false, new Location(player2.getWorld(), 123, 145, 11, 21, 31), Material.MAGENTA_BED);

        Assertions.assertEquals(new Location(player1.getWorld(), 2023, 35, 436, 93, 23), warp.getLocation());
        Assertions.assertEquals(new Location(player2.getWorld(), 123, 145, 11, 21, 31), warp2.getLocation());
    }

    @Test
    public void testLocationSetters(){

        PlayerMock player1 = server.addPlayer("1");
        PlayerMock player2 = server.addPlayer("2");

        Warp warp = new Warp("warp1", "Fubar", player1, true, new Location(player1.getWorld(), 2023, 35, 436, 93, 23), null);
        Warp warp2 = new Warp("warp2", "Fubar", player2, false, new Location(player2.getWorld(), 123, 145, 11, 21, 31), Material.MAGENTA_BED);

        warp.setLocation(new Location(player1.getWorld(), 2323, 3534, 436, 9, 1));
        warp2.setLocation(new Location(player2.getWorld(), 9, 0, 0, 8, 7));

        Assertions.assertEquals(new Location(player1.getWorld(), 2323, 3534, 436, 9, 1), warp.getLocation());
        Assertions.assertEquals(new Location(player2.getWorld(), 9, 0, 0, 8, 7), warp2.getLocation());
    }

    @Test
    public void testMaterialGetters(){

        PlayerMock player1 = server.addPlayer("1");
        PlayerMock player2 = server.addPlayer("2");

        Warp warp = new Warp("warp1", "Fubar", player1, true, new Location(player1.getWorld(), 20, 20, 20, 20, 20), null);
        Warp warp2 = new Warp("warp2", "Fubar", player2, false, new Location(player2.getWorld(), 1, 1, 1, 1, 1), Material.MAGENTA_BED);
        Warp warp3 = new Warp("warp3", "Fubar", player2, false, new Location(player2.getWorld(), 123, 134, 12, 1, 1), Material.ANGLER_POTTERY_SHERD);

        Assertions.assertNull(warp.getDisplayItem());
        Assertions.assertEquals(Material.MAGENTA_BED, warp2.getDisplayItem());
        Assertions.assertEquals(Material.ANGLER_POTTERY_SHERD, warp3.getDisplayItem());
    }

    @Test
    public void testMaterialSetters(){

        PlayerMock player1 = server.addPlayer("1");
        PlayerMock player2 = server.addPlayer("2");

        Warp warp = new Warp("warp1", "Fubar", player1, true, new Location(player1.getWorld(), 20, 20, 20, 20, 20), null);
        Warp warp2 = new Warp("warp2", "Fubar", player2, false, new Location(player2.getWorld(), 1, 1, 1, 1, 1), Material.MAGENTA_BED);
        Warp warp3 = new Warp("warp3", "Fubar", player2, false, new Location(player2.getWorld(), 123, 134, 12, 1, 1), Material.ANGLER_POTTERY_SHERD);

        warp.setDisplayItem(Material.BAMBOO_HANGING_SIGN);
        warp2.setDisplayItem(null);
        warp3.setDisplayItem(Material.BAMBOO);

        Assertions.assertEquals(Material.BAMBOO_HANGING_SIGN, warp.getDisplayItem());
        Assertions.assertNull(warp2.getDisplayItem());
        Assertions.assertEquals(Material.BAMBOO, warp3.getDisplayItem());
    }

    @Test
    public void testHashcode(){

        PlayerMock player1 = server.addPlayer("1");
        PlayerMock player2 = server.addPlayer("2");

        Warp warp = new Warp("warp1", "This is warp 1", player1, true, new Location(player1.getWorld(), 20, 20, 20, 20, 20), null);
        Warp warpEquals = new Warp("warp1", "This is warp 1", player1, true, new Location(player1.getWorld(), 20, 20, 20, 20, 20), null);

        Warp warp2 = new Warp("warp2", "This is warp 2", player2, false, new Location(player2.getWorld(), 1, 1, 1, 1, 1), Material.MAGENTA_BED);
        Warp warp2Equals = new Warp("warp2", "This is warp 2", player2, false, new Location(player2.getWorld(), 1, 1, 1, 1, 1), Material.MAGENTA_BED);

        Assertions.assertEquals(warp.hashCode(), warpEquals.hashCode());
        Assertions.assertEquals(warp2.hashCode(), warp2Equals.hashCode());
        Assertions.assertNotEquals(warp.hashCode(), warp2.hashCode());
    }

    @Test
    public void testEqualsAndHashcode(){
        PlayerMock player1 = server.addPlayer("1");
        PlayerMock player2 = server.addPlayer("2");

        Warp warp = new Warp("warp1", "This is warp 1", player1, true, new Location(player1.getWorld(), 20, 20, 20, 20, 20), null);
        Warp warpEquals = new Warp("warp1", "This is warp 1", player1, true, new Location(player1.getWorld(), 20, 20, 20, 20, 20), null);

        Warp warp2 = new Warp("warp2", "This is warp 2", player2, false, new Location(player2.getWorld(), 1, 1, 1, 1, 1), Material.MAGENTA_BED);
        Warp warp2Equals = new Warp("warp2", "This is warp 2", player2, false, new Location(player2.getWorld(), 1, 1, 1, 1, 1), Material.MAGENTA_BED);

        Assertions.assertTrue(warp.equals(warpEquals));
        Assertions.assertTrue(warp2.equals(warp2Equals));
        Assertions.assertFalse(warp.equals(warp2));

    }


    @AfterAll
    public static void tearDown(){
        MockBukkit.unmock();
    }

}