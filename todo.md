# SERVER

## Management API
### GET/POST/DELETE /api/v1/users

`UserDto {
    "name": "string",
    "groups": ["string", "string", ...]
}
`
### GET/POST/DELETE /api/v1/groups

`GroupDto {
    "name": "string"
}`

## Radio API

### POST /api/v1/transmission/start
### POST /api/v1/transmission/end

`TransmissionDto {
    "eventType": TransmissionEvent,
    "fromUser": "string",
    "toGroups": ["string", "string", ...]
}`

`enum TransmissionEvent {
    START, END, (SENDING)
}`

# CLIENT