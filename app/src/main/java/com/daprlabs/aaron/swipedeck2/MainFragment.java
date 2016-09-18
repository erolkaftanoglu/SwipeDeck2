package com.daprlabs.aaron.swipedeck2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.daprlabs.aaron.swipedeck.SwipeDeck;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erol on 14.09.2016.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private SwipeDeck cardStack;
    private Context context = getContext();
    private SwipeDeckAdapter adapter;
    private ArrayList<String> testData;
    private CheckBox dragCheckbox;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_main, container, false);
        cardStack = (SwipeDeck) v.findViewById(R.id.swipe_deck);
        dragCheckbox = (CheckBox) v.findViewById(R.id.checkbox_drag);
        testData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testData.add(String.valueOf(i));
        }

        adapter = new SwipeDeckAdapter(testData, getContext());
        if(cardStack != null){
            cardStack.setAdapter(adapter);
        }

        cardStack.setCallback(new SwipeDeck.SwipeDeckCallback() {
            @Override
            public void cardSwipedLeft(long itemId) {
                Log.i("MainActivity", "card was swiped left, position in adapter: " + itemId);

            }

            @Override
            public void cardSwipedRight(long itemId) {
                Log.i("MainActivity", "card was swiped right, position in adapter: " + itemId);
            }

            @Override
            public boolean isDragEnabled(long itemId) {
                return dragCheckbox.isChecked();
            }
        });

        cardStack.setLeftImage(R.id.left_image);
        cardStack.setRightImage(R.id.right_image);

        Button btn = (Button) v.findViewById(R.id.button_left);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStack.swipeTopCardLeft(500);

            }
        });
        Button btn2 = (Button) v.findViewById(R.id.button_right);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStack.swipeTopCardRight(180);
            }
        });

        Button btn3 = (Button) v.findViewById(R.id.button_center);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                testData.add("a sample string.");
//                adapter.notifyDataSetChanged();
                cardStack.unSwipeCard();
            }
        });


        return v;

    }


    public class SwipeDeckAdapter extends BaseAdapter {

        private List<String> data;
        private Context context;

        public SwipeDeckAdapter(List<String> data, Context context) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = convertView;
            if (v == null) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                // normally use a viewholder
                v = inflater.inflate(R.layout.test_card2, parent, false);
            }
            //((TextView) v.findViewById(R.id.textView2)).setText(data.get(position));
            ImageView imageView = (ImageView) v.findViewById(R.id.offer_image);
            Picasso.with(context).load(R.drawable.food).fit().centerCrop().into(imageView);
            TextView textView = (TextView) v.findViewById(R.id.sample_text);
            String item = (String)getItem(position);
            textView.setText(item);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Layer type: ", Integer.toString(v.getLayerType()));
                    Log.i("Hardware Accel type:", Integer.toString(View.LAYER_TYPE_HARDWARE));
                    /*Intent i = new Intent(v.getContext(), BlankActivity.class);
                    v.getContext().startActivity(i);*/
                }
            });
            return v;
        }
    }

}
