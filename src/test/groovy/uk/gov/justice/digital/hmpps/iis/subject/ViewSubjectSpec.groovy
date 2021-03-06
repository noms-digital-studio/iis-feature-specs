package uk.gov.justice.digital.hmpps.iis.subject

import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.iis.pages.PrintPage
import uk.gov.justice.digital.hmpps.iis.pages.SearchPage
import uk.gov.justice.digital.hmpps.iis.pages.SearchResultsPage
import uk.gov.justice.digital.hmpps.iis.pages.SubjectDetailsPage
import uk.gov.justice.digital.hmpps.iis.util.SignOnBaseSpec

@Stepwise
class ViewSubjectSpec extends SignOnBaseSpec {

    def setupSpec() {
        signIn()
    }

    def cleanupSpec() {
        signOut()
    }

    def 'Select search result to view subject detail'() {

        when: 'I do a search that gives some results'
        performSearch([surname: 'surnamec'])

        and: 'I click the first result'
        resultItemLinks[0].click()

        then: 'I see the subject details page'
        at SubjectDetailsPage

        and: 'I see the surname I searched for in upper case'
        subjectName.text().contains('SURNAMEC')

        and: 'I see the prison identifier'
        subjectId.verifyNotEmpty()

        and: 'I see the parole ref'
        paroleRef.text().contains('AA12331, AA12332')

        and: 'I see the PNC'
        pnc.text().contains('012345/99G')

        and: 'I see the summary view'
        browser.currentUrl.endsWith('/summary')

        and: 'The page url contains the same prison identifier'
        browser.currentUrl.contains(subjectIdNumber)
    }

    def 'When viewing a subject I can return to the search results'() {

        when: 'I view a subject'
        performSearch([surname: 'surnamec'])
        resultItemLinks[0].click()

        then: 'I see the subject page'
        at SubjectDetailsPage

        and: 'There is a link back to search results'
        backToResults.isDisplayed()

        when: 'I click the back to results link'
        backToResults.click()

        then: 'I see the search results page'
        at SearchResultsPage
    }

    def 'When viewing a subject with no other identifiers, I still see the prison number' () {

        when: 'I view a subject with no additional identifiers'
        performSearch([surname: 'surnamem'])
        resultItemLinks[0].click()

        then: 'I see the subject page'
        at SubjectDetailsPage

        and: 'I see the prison identifier'
        subjectId.verifyNotEmpty()
    }

    def 'When viewing a subject I can navigate to the print page' () {

        when: 'I view a subject with no additional identifiers'
        performSearch([surname: 'surnamem'])
        resultItemLinks[0].click()

        then: 'I see the subject page'
        at SubjectDetailsPage

        and: 'I see the save to pdf link'
        saveToPdf.verifyNotEmpty()

        when: 'I click the save to pdf link'
        saveToPdf.click()

        then: 'I see the print page'
        at PrintPage
    }

    private void performSearch(query) {
        to SearchPage
        searchForm.nameAge(query)
        at SearchResultsPage
    }

}
