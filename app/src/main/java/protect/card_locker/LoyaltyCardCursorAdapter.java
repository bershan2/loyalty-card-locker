package protect.card_locker;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class LoyaltyCardCursorAdapter extends CursorAdapter
{
    public LoyaltyCardCursorAdapter(Context context, Cursor cursor)
    {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return LayoutInflater.from(context).inflate(R.layout.loyalty_card_layout, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        // Find fields to populate in inflated template
        ImageView thumbnail = view.findViewById(R.id.thumbnail);
        TextView storeField = (TextView) view.findViewById(R.id.store);
        TextView noteField = (TextView) view.findViewById(R.id.note);

        // Extract properties from cursor
        LoyaltyCard loyaltyCard = LoyaltyCard.toLoyaltyCard(cursor);

        // Populate fields with extracted properties
        storeField.setText(loyaltyCard.store);

        if(loyaltyCard.note.isEmpty() == false)
        {
            noteField.setVisibility(View.VISIBLE);
            noteField.setText(loyaltyCard.note);
        }
        else
        {
            noteField.setVisibility(View.GONE);
        }

        int tileLetterFontSize = context.getResources().getDimensionPixelSize(R.dimen.tileLetterFontSize);
        int pixelSize = context.getResources().getDimensionPixelSize(R.dimen.cardThumbnailSize);
        LetterBitmap letterBitmap = new LetterBitmap(context, loyaltyCard.store, loyaltyCard.store, tileLetterFontSize, pixelSize, pixelSize);
        thumbnail.setImageBitmap(letterBitmap.getLetterTile());
    }
}
