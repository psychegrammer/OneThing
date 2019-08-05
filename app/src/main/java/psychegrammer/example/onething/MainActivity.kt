package psychegrammer.example.onething

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import psychegrammer.example.onething.data.ThingsDatabaseHandler
import psychegrammer.example.onething.model.Thing

class MainActivity : AppCompatActivity() {

    var dbHandler: ThingsDatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHandler = ThingsDatabaseHandler(this)

        var thing = Thing()
        thing.thingName = "Pranje garderobe"
        thing.assignedTo = "Mustafa"
        thing.assignedBy = "Bapko"
        dbHandler!!.createThing(thing)

        // Read from database
        var things: Thing = dbHandler!!.readAThing(1)

        Log.d("Item:", things.thingName)

    }


}
