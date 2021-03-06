package uk.gov.justice.digital.hmpps.iis.pages.subject

import uk.gov.justice.digital.hmpps.iis.pages.SubjectDetailsPage


class HdcInfoPage extends SubjectDetailsPage {

    static at = {
        browser.currentUrl.contains('/subject/')
        browser.currentUrl.endsWith('/hdcinfo')
    }

    static content = {
        recallsList { $('div.recall') }
        historyList { $('div.history') }
    }
}
