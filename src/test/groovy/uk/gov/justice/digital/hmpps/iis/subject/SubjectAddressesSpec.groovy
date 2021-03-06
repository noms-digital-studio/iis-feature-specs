package uk.gov.justice.digital.hmpps.iis.subject

import spock.lang.Stepwise
import uk.gov.justice.digital.hmpps.iis.pages.SearchPage
import uk.gov.justice.digital.hmpps.iis.pages.SubjectDetailsPage
import uk.gov.justice.digital.hmpps.iis.pages.subject.AddressesPage
import uk.gov.justice.digital.hmpps.iis.util.SignOnBaseSpec

@Stepwise
class SubjectAddressesSpec extends SignOnBaseSpec {

    def setupSpec() {
        signIn()
    }

    def cleanupSpec() {
        signOut()
    }

    def 'List of addresses shown, excluding ones where no entry in address line 1'() {

        when: 'I view the addresses section'
        gotoAddressesPage([surname: 'surnamea'])

        then: 'I see the addresses for that subject'
        addressList.size() == 2

        and: 'Addresses have type and name and address correctly capitalized, when present'
        $('#address0-type').text().equals('Other')
        $('#address0-name').text().equals('First Lasta')
        $('#address0-line1').text().equals('1, Street Road')
    }

    private void gotoAddressesPage(query) {
        performSearch(query)
        resultItemLinks[0].click()
        at SubjectDetailsPage
        section('addresses')
        at AddressesPage
    }

    private void performSearch(query) {
        to SearchPage
        searchForm.nameAge(query)
    }
}
