package uk.gov.justice.digital.hmpps.iis.pages

import geb.Page
import uk.gov.justice.digital.hmpps.iis.modules.ErrorsModule
import uk.gov.justice.digital.hmpps.iis.modules.HeaderModule
import uk.gov.justice.digital.hmpps.iis.modules.SearchFormModule

class NamesPage extends Page {

    static url = '/search/names'

    static at = {
        title == 'Enter at least one name'
        browser.currentUrl.contains('/search/names')
    }

    static content = {

        header { module(HeaderModule) }

        errors { module(ErrorsModule) }

        searchForm { module(SearchFormModule)}
    }
}
