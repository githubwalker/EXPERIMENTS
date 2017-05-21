package code;

/**
 * Created by andrew on 21.05.2017.
 */
public class ProfilingController implements ProfilingControllerMBean
{
    private boolean enabled = false;

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
}


