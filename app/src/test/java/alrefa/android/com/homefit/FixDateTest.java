package alrefa.android.com.homefit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import alrefa.android.com.homefit.Utils.AppUtils;
import alrefa.android.com.homefit.Utils.calendar.CivilDate;

@RunWith(MockitoJUnitRunner.class)
public class FixDateTest {


    private String sampleDate;
    private CivilDate civilDate;
    private String sampleTime;
    private String sample_location;


    @Before
    public void before() {
        sampleDate = "2019-05-06";
        sampleTime = "00:00:00";
        sample_location = "21.3968973,51.6568035";
        civilDate = new CivilDate();
        civilDate.setYear(2019);
        civilDate.setMonth(05);
        civilDate.setDayOfMonth(06);
    }

    @Test
    public void testDates() {

    }

    @Test
    public void getYearTest() {
        String year = sampleDate.substring(0, sampleDate.indexOf("-"));
        Assert.assertEquals("2019", year);
        Assert.assertEquals(sampleDate.indexOf("-"), 4);
    }

    @Test
    public void getMonthTest() {
        String month = sampleDate.substring(sampleDate.indexOf("-") + 1, sampleDate.lastIndexOf("-"));
        Assert.assertEquals("05", month);
    }


    @Test
    public void getDayTest() {
        String day = sampleDate.substring(sampleDate.lastIndexOf("-") + 1);
        Assert.assertEquals("06", day);
    }

    @Test
    public void getDayOfWeekName() {
        String name = AppUtils.getEnglishDayOfWeekName(civilDate.getDayOfWeek());
        Assert.assertEquals("Monday", name);
    }


    @Test
    public void fixTimeText() {
        String time = sampleTime.substring(0,sampleTime.lastIndexOf(":"));
        Assert.assertEquals("00:00",time);
    }

    @Test
    public void testLatLocationFix(){
        String lat = sample_location.substring(0,sample_location.indexOf(","));
        Assert.assertEquals("21.3968973",lat);
    }

    @Test
    public void testLngLocation(){
        String lng = sample_location.substring(sample_location.indexOf(",")+1);
        Assert.assertEquals("51.6568035",lng);
    }

}
