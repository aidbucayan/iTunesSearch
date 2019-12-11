
For persistence data i use Shared preferences to saved the last searched inputs
Why only searched input? since the price, link etc may changed we need to get a live and up to date
response from the server.

For architecture is used MVVM (Model View ViewModel) it's because ,
MVVM gives more separation of concerns, less boilerplate and encourage reactive approach.
Additionally, it is officially recommended tools straight from Google,
so we can worry less about lifecycle, memory leaks and crashes.


# iTunesSearch