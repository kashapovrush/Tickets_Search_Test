package com.kashapovrush.ticketssearchtest

import android.app.Application
import com.kashapovrush.country_selected_screen.di.CountrySelectedComponent
import com.kashapovrush.country_selected_screen.di.CountrySelectedComponentProvider
import com.kashapovrush.country_selected_screen.di.DaggerCountrySelectedComponent
import com.kashapovrush.country_selected_screen.ui.CountrySelectedFragment
import com.kashapovrush.main_screen.ui.di.DaggerMainComponent
import com.kashapovrush.main_screen.ui.di.MainComponent
import com.kashapovrush.main_screen.ui.di.MainComponentProvider
import com.kashapovrush.tickets_screen.di.DaggerTicketsComponent
import com.kashapovrush.tickets_screen.di.TicketsComponent
import com.kashapovrush.tickets_screen.di.TicketsComponentProvider

class AviaApp: Application(), MainComponentProvider, CountrySelectedComponentProvider,
    TicketsComponentProvider {


    override fun getMainComponent(): MainComponent {
        return DaggerMainComponent.factory().create(this)
    }

    override fun getCountrySelectedComponent(): CountrySelectedComponent {
        return DaggerCountrySelectedComponent.factory().create(this)
    }

    override fun getTicketsComponent(): TicketsComponent {
        return DaggerTicketsComponent.factory().create(this)
    }


}