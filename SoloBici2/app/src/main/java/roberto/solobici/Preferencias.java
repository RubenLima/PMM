package roberto.solobici;

/**
 *
 */
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.roberto.solobici.R;

public class Preferencias extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }

}
