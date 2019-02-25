package com.example.oliver.artistsearch.ui.utils.rx;

import io.reactivex.Scheduler;


public interface SchedulerProvider {

    Scheduler ui();

    Scheduler io();

}
