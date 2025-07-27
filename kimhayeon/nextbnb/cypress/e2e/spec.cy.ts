describe('template spec', () => {
  it('passes', () => {
    cy.visit('https://example.cypress.io')
  })
})

describe('fail test', () => {
  it('fail test!', () => {
    expect(true).to.equal(false)
  })
})
