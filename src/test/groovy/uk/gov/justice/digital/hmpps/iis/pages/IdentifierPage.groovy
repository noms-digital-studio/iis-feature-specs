package uk.gov.justice.digital.hmpps.iis.pages

import geb.Page
import uk.gov.justice.digital.hmpps.iis.modules.ErrorsModule
import uk.gov.justice.digital.hmpps.iis.modules.HeaderModule
import uk.gov.justice.digital.hmpps.iis.modules.SearchFormModule

class IdentifierPage extends Page {

    static url = '/search/identifier'

    static at = {
        browser.currentUrl.contains('/search/identifier')
        title == 'Enter at least one unique identifier'
    }

    static content = {

        header { module(HeaderModule) }

        errors { module(ErrorsModule) }

        searchForm { module(SearchFormModule)}
    }
}
