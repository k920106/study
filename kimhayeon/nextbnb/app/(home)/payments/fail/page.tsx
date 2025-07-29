import PaymentFail from '@/components/Payment/PaymentFail'
import { Suspense } from 'react'

export default function Page() {
  return (
    <Suspense fallback={<div>Loading...</div>}>
      <PaymentFail />
    </Suspense>
  )
}
