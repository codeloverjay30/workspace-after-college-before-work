package com.jay30.toastydemo1

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jay30.toastydemo1.ui.theme.greenColor
import es.dmoral.toasty.Toasty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToastyDemo1Frame() {
    // on below line we are specifying
    // theme as scaffold.
    Scaffold(

        // in scaffold we are
        // specifying top bar.
        topBar = {

            // inside top bar we are specifying background color.
            TopAppBar(
                // along with that we are specifying
                // title for our top bar.
                title = {

                    // in the top bar we are specifying
                    // tile as a text
                    Text(

                        // on below line we are specifying
                        // text to display in top app bar.
                        text = "Custom Toast in Android",

                        // on below line we are specifying modifier
                        // to fill max width.
                        modifier = Modifier.fillMaxWidth(),

                        // on below line we are specifying
                        // text alignment.
                        textAlign = TextAlign.Center,

                        // on below line we are specifying
                        // color for our text.
                        color = Color.White
                    )
                })
        }) { innerPadding ->
        // on below line we are calling custom toast
        // ui method to display ui for our custom toast
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                customToastUI()
            }
        }
    }
}


@Composable
fun customToastUI() {
    // on below line we are creating a
    // variable for context
    val ctx = LocalContext.current

    // on below line we are
    // creating a column for our ui.
    Column(
        // in this column we are adding a modifier
        // for our column and specifying max width,
        // height and size.
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize()

            // on below line we are adding padding
            // from all sides to our column.
            .padding(6.dp),

        // on below line we are adding vertical arrangement
        // for our column as bottom
        verticalArrangement = Arrangement.Center,

        // on below line we are adding horizontal alignment
        // for our column.
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // on below line we are
        // displaying a simple text
        Text(

            // on below line we are specifying modifier
            // as padding for our text.
            modifier = Modifier.padding(6.dp),

            // on below line we are specifying
            // text as normal image.
            text = "Custom Toast Message",

            // on below line we are specifying
            // font weight as bold.
            fontWeight = FontWeight.Bold,

            // on below line we are
            // setting color for our text
            color = greenColor,

            // on below line we are
            // setting font size.
            fontSize = 20.sp
        )

        // on below line we are creating a simple
        // spacer with height 20
        Spacer(modifier = Modifier.height(20.dp))

        // on below line we are creating a button
        // for displaying error toast
        Button(
            // on below line we are adding width
            // for our button and padding to it.
            modifier = Modifier
                .width(300.dp)
                .padding(7.dp),

            // in this button we are adding
            // on click for it on below line.
            onClick = {

                // on below line we are displaying a custom
                // toast message on below line for error toast
                Toasty.error(ctx, "This is an error toast.", Toast.LENGTH_SHORT, true).show()

            }) {
            // on below line we are
            // specifying text for button.
            Text(text = "Error Toast")
        }

        // on below line we are creating a spacer of height 20
        Spacer(modifier = Modifier.height(20.dp))

        // on below line we are creating a button
        // for displaying a toast
        Button(
            // on below line we are adding width for our
            // button and padding to it.
            modifier = Modifier
                .width(300.dp)
                .padding(7.dp),

            // in this button we are adding on
            // click for it on below line.
            onClick = {

                // on below line we are displaying a custom toast message on below line
                Toasty.success(ctx, "This is a Success toast.", Toast.LENGTH_SHORT, true).show()

            }) {
            // on below line we are
            // specifying text for button.
            Text(text = "Success Toast")
        }

        // on below line we are creating a spacer of height 20
        Spacer(modifier = Modifier.height(20.dp))

        // on below line we are creating a
        // button for displaying a toast
        Button(
            // on below line we are adding width
            // for our button and padding to it.
            modifier = Modifier
                .width(300.dp)
                .padding(7.dp),

            // in this button we are adding
            // on click for it on below line.
            onClick = {

                // on below line we are displaying a custom toast message on below line
                Toasty.info(ctx, "This is a Info toast.", Toast.LENGTH_SHORT, true).show()

            }) {
            // on below line we are specifying
            // text for button.
            Text(text = "Info Toast")
        }

        // on below line we are creating a spacer of height 20
        Spacer(modifier = Modifier.height(20.dp))

        // on below line we are creating a
        // button for displaying a toast
        Button(
            // on below line we are adding
            // width for our button and padding to it.
            modifier = Modifier
                .width(300.dp)
                .padding(7.dp),

            // in this button we are adding on click for it on below line.
            onClick = {

                // on below line we are displaying a custom toast message on below line
                Toasty.warning(ctx, "This is a Warning toast.", Toast.LENGTH_SHORT, true).show()

            }) {
            // on below line we are specifying text for button.
            Text(text = "Warning Toast")
        }

        // on below line we are creating a spacer of height 20
        Spacer(modifier = Modifier.height(20.dp))

        // on below line we are creating a
        // button for displaying a toast
        Button(
            // on below line we are adding width for our
            // button and padding to it.
            modifier = Modifier
                .width(300.dp)
                .padding(7.dp),

            // in this button we are adding on
            // click for it on below line.
            onClick = {

                // on below line we are displaying a custom toast
                // message on below line
                Toasty.normal(ctx, "This is a Normal toast.").show()

            }) {
            // on below line we are specifying
            // text for button.
            Text(text = "Normal Toast")
        }


        // on below line we are creating a spacer of height 20
        Spacer(modifier = Modifier.height(20.dp))

        // on below line we are creating a
        // button for displaying a toast
        Button(
            // on below line we are adding width
            // for our button and padding to it.
            modifier = Modifier
                .width(300.dp)
                .padding(7.dp),

            // in this button we are adding on click
            // for it on below line.
            onClick = {

                // on below line we are displaying a custom toast
                // message on below line
                Toasty.custom(
                    ctx,
                    "This is a Custom toast.",
                    R.drawable.ic_launcher_background,
                    R.color.purple_200,
                    3000,
                    true,
                    true
                ).show()

            }) {
            // on below line we are specifying
            // text for button.
            Text(text = "Custom Toast")
        }

    }
}