# XapoDemo

This Demo Consists of MVVM Architechture with Architectural Components like LiveData, ViewModel and libraries like EventBus, ButterKnife, Retrofit2.

The flows goes like this:

    View (Activities) -> ViewModel -> Engine (layer for network call)

There are several packages in this project let's discuss one by one:

1. "engine" Package and "rest" Package: 
    This consists of files in rest layer meaning these are responsible for fetching the data.
2. "eventbus" Package: 
    This consists of EventBus interfaces to use which is pushing data from one layer to another.
3. "events" Package: 
    This consists of interfaces for eventBus to use.
4. "factory" Package:
    These files use Factory Pattern to get the instances of Engine Layer so that ViewModel can use.
5. "pojo" Package:
    These are different POJO's required for the network call and Reponse.
6. "threading" Package:
    This consists of files used for creating threads and to keep main thread away from doing any operations.
7. "viewmodel" Package:
    This consists of ViewModels of different activities.
