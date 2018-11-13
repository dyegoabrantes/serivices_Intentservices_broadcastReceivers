package saci.development.entrega03;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class fragmentPageAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public fragmentPageAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new fragment1();
        }  if (position == 1) {
            return new fragment2();
        }  if (position == 2) {
            return new fragment3();
        }  if (position == 3) {
            return new fragment4();
        }  else {
            return new fragment5();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 5;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Questão 1";
            case 1:
                return "Questão 2";
            case 2:
                return "Comparação";
            case 3:
                return "Questão 4";
            case 4:
                return "Questão 5";
            default:
                return null;
        }
    }

}