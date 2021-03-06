package uk.gov.justice.digital.hmpps.iis.site

import spock.lang.Stepwise
import spock.lang.Unroll
import uk.gov.justice.digital.hmpps.iis.pages.PrintPage
import uk.gov.justice.digital.hmpps.iis.pages.SearchPage
import uk.gov.justice.digital.hmpps.iis.pages.SubjectDetailsPage
import uk.gov.justice.digital.hmpps.iis.util.SignOnBaseSpec

@Stepwise
class PrintSpec extends SignOnBaseSpec {

    def setupSpec() {
        signIn()
    }

    def cleanupSpec() {
        signOut()
    }

    def 'Must select a print option'() {

        when: 'I am perform a search and click save'
        performSearchAndSave()

        then: 'I see the print page'
        at PrintPage

        when: 'I continue without selecting a print option'
        proceed()

        then: 'I see an error message'
        errors.summaryShown()
    }

    def 'Can choose summary, sentence summary, movements, hdc, offences, custody offences, aliases and addresses'() {

        when: 'I am perform a search and click save'
        performSearchAndSave()

        then: 'I see the print page'
        at PrintPage

        and: 'I see the correct number of print options'
        printOptions.size == 10
    }

    def 'I can return to subject page with back button'() {

        when: 'I am perform a search and click save'
        performSearchAndSave()

        then: 'I see the print page'
        at PrintPage

        and: 'I see the back button'
        backLink.isDisplayed()

        when: 'I click the back button'
        backLink.click()

        then: 'I return to the subject page'
        at SubjectDetailsPage
    }

    @Unroll
    def 'downloading pdf does not result in page change'() {

        when: 'I am perform a search and click save'
        performSearchAndSave()

        then: 'I see the print page'
        at PrintPage

        when: 'I choose a print option'
        selectPrintOptions(['subject'])
        proceed()

        then: 'I remain on the print page'
        at PrintPage
    }

    private void performSearchAndSave() {
        to SearchPage
        searchForm.nameAge([surname: 'surnamec'])
        resultItemLinks[0].click()
        at SubjectDetailsPage
        saveToPdf.click()
    }

}
